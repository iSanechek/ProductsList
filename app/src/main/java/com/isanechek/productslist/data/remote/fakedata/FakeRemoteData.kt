package com.isanechek.productslist.data.remote.fakedata

import com.isanechek.productslist.data.remote.dto.FakeProductDTO

interface FakeRemoteData {

    fun generationFakeData(itemsSize: Int): List<FakeProductDTO>
}