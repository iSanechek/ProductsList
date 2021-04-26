package com.isanechek.productslist.data.remote.fakedata.impl

import com.isanechek.productslist.data.remote.fakedata.FakeRemoteData
import com.isanechek.productslist.data.remote.dto.FakeProductDTO
import com.isanechek.productslist.data.remote.utils.LONG_TEXT
import com.isanechek.productslist.utils.log
import kotlin.random.Random

class FakeRemoteDataImpl : FakeRemoteData {

    // список категорий
    // shuffled - для разнообразия
    private val categories = listOf(
        JACKET,
        WINDBREAKERS,
        PULLOVER,
        SHORTS,
        PANTS,
        JEANS,
        T_SHIRTS,
        DRESS,
        SKIRTS,
        FOOTWEAR
    )

    override fun generationFakeData(itemsSize: Int): List<FakeProductDTO> {
        val temp = mutableListOf<FakeProductDTO>()
        val size = categories.size
        val numberForGeneration = getRandomNumber(size / 2, size)

        for (i in 0 until itemsSize) {
            val category = categories.random()
            val fakeArticle = generationFakeArticle(numberForGeneration)
            val companyName = getCompanyName(category)
            val price = getPrice()
            val timestamp = System.currentTimeMillis()
            val fakeDescription = generationFakeDescription()
            val url = getProductCoverUrl(category)
            val product = FakeProductDTO(
                model = fakeArticle,
                category = category,
                companyName = companyName,
                price = price,
                timestamp = timestamp,
                description = fakeDescription,
                coverUrl = url
            )
            temp.add(product)
        }

        return temp
    }

    private fun getProductCoverUrl(category: String): String = when (category) {
        JACKET -> listOf(
            "https://a.lmcdn.ru/img600x866/M/P/MP002XB00K9V_10781021_1_v1.jpg",
            "https://a.lmcdn.ru/img600x866/M/P/MP002XB00YRT_13556210_1_v1.jpg",
            "https://a.lmcdn.ru/img600x866/M/P/MP002XB00K9W_10803734_1_v1.jpg"
        ).random()
        WINDBREAKERS -> listOf(
            "https://a.lmcdn.ru/img600x866/N/I/NI464EMJOET6_12237546_1_v1_2x.jpg",
            "https://a.lmcdn.ru/img600x866/N/I/NI464EMJOEW4_11675037_1_v1_2x.jpg",
            "https://a.lmcdn.ru/img600x866/N/I/NI464EMLZPX9_13343610_1_v2_2x.jpg"
        ).random()
        PULLOVER -> listOf(
            "https://a.lmcdn.ru/img600x866/T/O/TO263EBCCBU4_7065202_1_v1.jpg",
            "https://a.lmcdn.ru/img600x866/T/O/TO263EBKGWE8_11988322_3_v1.jpg",
            "https://a.lmcdn.ru/img600x866/A/N/AN511EBJPCG8_11953458_1_v1.jpg"
        ).random()
        SHORTS -> listOf(
            "https://a.lmcdn.ru/img600x866/M/A/MA002EWMPDT5_13483099_1_v1_2x.jpg",
            "https://a.lmcdn.ru/img600x866/M/A/MA002EWMCOZ8_13131433_1_v1.jpg",
            "https://a.lmcdn.ru/img600x866/M/A/MA002EWLWSW1_12816249_1_v1.jpg"
        ).random()
        PANTS -> listOf(
            "https://a.lmcdn.ru/img600x866/M/P/MP002XW04LPW_13016139_1_v1_2x.jpg",
            "https://a.lmcdn.ru/img600x866/M/P/MP002XW057K9_13282065_1_v1_2x.jpg",
            "https://a.lmcdn.ru/img600x866/M/P/MP002XW05CM3_13357781_1_v1_2x.jpg"
        ).random()
        JEANS -> listOf(
            "https://a.lmcdn.ru/img600x866/L/E/LE306EMJLNV0_11755306_1_v1_2x.jpg",
            "https://a.lmcdn.ru/img600x866/L/E/LE306EWLWQG3_12822836_1_v1_2x.jpg",
            "https://a.lmcdn.ru/img600x866/L/E/LE306EWLWQT1_13570768_1_v1_2x.jpg"
        ).random()
        T_SHIRTS -> listOf(
            "https://a.lmcdn.ru/img600x866/M/P/MP002XB00N04_11561672_1_v1.jpg",
            "https://a.lmcdn.ru/img600x866/M/P/MP002XB00M6W_11323543_3_v1.jpg",
            "https://a.lmcdn.ru/img600x866/M/P/MP002XB00N03_11557042_1_v1.jpg"
        ).random()
        DRESS -> listOf(
            "https://a.lmcdn.ru/img600x866/C/H/CH991EGMFQV4_13786543_1_v1.jpg",
            "https://a.lmcdn.ru/img600x866/C/H/CH991EGJXGB4_12208362_1_v1.jpg",
            "https://a.lmcdn.ru/img600x866/C/H/CH991EGMFTE7_13068761_1_v1.jpg"
        ).random()
        SKIRTS -> listOf(
            "https://a.lmcdn.ru/img600x866/M/P/MP002XW04L6Z_12996314_1_v1_2x.jpeg",
            "https://a.lmcdn.ru/img600x866/M/P/MP002XW0EB53_11743018_1_v2_2x.jpeg",
            "https://a.lmcdn.ru/img600x866/M/P/MP002XW03IS2_12414994_1_v7_2x.jpg"
        ).random()
        FOOTWEAR -> listOf(
            "https://a.lmcdn.ru/img600x866/I/X/IX001XW00TAZ_13015245_1_v1.jpeg",
            "https://a.lmcdn.ru/img600x866/I/X/IX001XW00TWY_13103660_1_v3.jpg",
            "https://a.lmcdn.ru/img600x866/S/P/SP014AWKEOC1_12692062_2_v1.jpg"
        ).random()
        else -> throw IllegalArgumentException("Category not find!")
    }

    private fun generationFakeDescription(): String {
        val number = getRandomNumber(5, 10)
        return LONG_TEXT
            .split(",")
            .map { it.trim() }
            .toList()
            .shuffled()
            .take(number)
            .joinToString("")
            .replace("\n", "")
    }

    private fun getPrice(): Int = Random.nextInt(100, 1000)

    private fun getCompanyName(category: String): String = when (category) {
        JACKET -> listOf("Canada Goose", "Columbia", "The North Face").random()
        WINDBREAKERS -> listOf("Patagonia Houdini", "Helly Hansen SEVEN J", "Adidas Tiro").random()
        PULLOVER -> listOf("ARAN CRAFTS", "WOOLOVERS", "LORO PIANA").random()
        SHORTS -> listOf("Nike", "Adidas", "Armani").random()
        PANTS -> listOf("H&M", "Hugo Boss", "BOSS").random()
        JEANS -> listOf("Levi's", "Calvin Klein", "Armani Jeans").random()
        T_SHIRTS -> listOf("Hermes", "Louis Vuitton", "Gucci").random()
        DRESS -> listOf("Zarina", "VERA NOVA", "Valeri Marcon").random()
        SKIRTS -> listOf("O'STIN", "Mohito", "ZARA").random()
        FOOTWEAR -> listOf("Timberland", "Mascotte", "Tervolina").random()
        else -> throw IllegalArgumentException("Category not find!")
    }

    private fun generationFakeArticle(number: Int): String = List(number) {
        (('A'..'Z') + ('a'..'z')).random()
    }.joinToString(separator = "")

    private fun getRandomNumber(fromNumber: Int, toNumber: Int): Int =
        Random.nextInt(fromNumber, toNumber)

    companion object {
        private const val JACKET = "куртки"
        private const val WINDBREAKERS = "ветровки"
        private const val PULLOVER = "свитер"
        private const val SHORTS = "шорты"
        private const val PANTS = "брюки"
        private const val JEANS = "джинсы"
        private const val T_SHIRTS = "футболка"
        private const val DRESS = "платье"
        private const val SKIRTS = "юбки"
        private const val FOOTWEAR = "обувь"
    }
}