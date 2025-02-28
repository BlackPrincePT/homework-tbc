package com.perullheim.homework.presentation.maps

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.perullheim.homework.R
import com.perullheim.homework.databinding.FragmentMapsBinding
import com.perullheim.homework.presentation.BaseFragment
import com.perullheim.homework.presentation.maps.model.Address
import com.perullheim.homework.utils.collectLatest
import com.perullheim.homework.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsFragment : BaseFragment<FragmentMapsBinding>(FragmentMapsBinding::inflate),
    OnMapReadyCallback {

    private val viewModel: MapsViewModel by viewModels()

    private var googleMap: GoogleMap? = null
    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissions()
    }

    override fun setup() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        subscribeToViewStateUpdates()
    }

    override fun onMapReady(map: GoogleMap) {
        if (!isLocationEnabled(requireContext())) {
            AlertDialog.Builder(requireContext()).apply {
                setTitle("Location")
                setMessage("Application needs location access to work")
                setPositiveButton("Settings") { _, _ ->
                    Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                        .also(this@MapsFragment::startActivity)
                }
                create()
                show()
            }
            return
        }

        googleMap = map
        googleMap?.uiSettings?.isZoomControlsEnabled = true

        val isFineGranted =
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

        val isCoarseGranted =
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

        if (!isFineGranted && !isCoarseGranted) {
            return
        }

        googleMap?.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let(this::markLocation)
        }

        googleMap?.setOnMarkerClickListener { marker ->
            val markerAddress = Address(
                lat = marker.position.latitude,
                lan = marker.position.longitude,
                title = marker.title.orEmpty(),
                address = marker.title.orEmpty()
            )
            findNavController().navigate(
                MapsFragmentDirections.actionMapsFragmentToMarkerInfoBottomSheetFragment(
                    markerAddress
                )
            )
            true
        }
    }

    private fun requestPermissions() {
        val locationPermissionRequest =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                when {
                    permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                        view?.showSnackBar("Location services enabled")
                    }

                    permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                        view?.showSnackBar("Location services enabled")
                    }

                    else -> {
                        view?.showSnackBar("Location permission is required")
                    }
                }
            }
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun isLocationEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            locationManager.isLocationEnabled
        } else {
            val gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            gpsEnabled || networkEnabled
        }
    }

    private fun markLocation(location: Location) {
        val currentPosition = LatLng(location.latitude, location.longitude)

        googleMap?.addMarker(MarkerOptions().position(currentPosition).title("Current Location"))
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, ZOOM_LEVEL))
    }

    private fun createMarker(address: Address): MarkerOptions = with(address) {
        val coordinates = LatLng(lat, lan)
        return MarkerOptions().position(coordinates).title(title)
    }

    private fun subscribeToViewStateUpdates() {
        collectLatest(viewModel.state) { state ->

            state.addresses.getContentIfNotHandled()?.map { address ->
                googleMap?.addMarker(createMarker(address))
            }

            state.errorMsg?.getContentIfNotHandled()?.let {
                view?.showSnackBar(it)
            }
        }
    }

    companion object {
        private const val ZOOM_LEVEL = 13f
    }
}