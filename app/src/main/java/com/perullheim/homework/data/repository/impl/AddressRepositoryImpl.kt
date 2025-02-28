package com.perullheim.homework.data.repository.impl

import com.perullheim.homework.data.remote.AddressService
import com.perullheim.homework.data.remote.NetworkUtils
import com.perullheim.homework.data.remote.model.AddressDto
import com.perullheim.homework.data.repository.abstraction.AddressRepository
import com.perullheim.homework.utils.Resource
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    private val addressService: AddressService,
    private val networkUtils: NetworkUtils
) : AddressRepository {

    override suspend fun fetchAddresses(): Resource<List<AddressDto>> {
        return networkUtils.handleHttpRequest { addressService.fetchAddresses() }
    }
}