package com.perullheim.homework.data.repository.abstraction

import com.perullheim.homework.data.remote.model.AddressDto
import com.perullheim.homework.utils.Resource

interface AddressRepository {
    suspend fun fetchAddresses(): Resource<List<AddressDto>>
}