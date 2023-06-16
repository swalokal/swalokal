package com.capstone.swalokal.data

import com.capstone.swalokal.data.api.response.PredictItem

data class ActualResponse(
    val error: Boolean,
    val data: List<PredictItem>,
)

fun getActualResponseJsonResponse(): String {
    return """
            {
                "error": false,
                "data": [
                    {
                        "id": 1,
                        "name": "Pop Mie",
                        "price": 6000,
                        "toko": "indra keenz",
                        "latitude": 3.35729312896729,
                        "longitude": 99.37930297851562,
                        "url": "https://storage.googleapis.com/swalokal_store/toko/toko-1.jpg"
                    },
                    {
                        "id": 2,
                        "name": "Oishi",
                        "price": 1500,
                        "toko": "indra keenz",
                        "latitude": 3.35729312896729,
                        "longitude": 99.37930297851562,
                        "url": "https://storage.googleapis.com/swalokal_store/toko/toko-1.jpg"
                    },
                    {
                        "id": 3,
                        "name": "Mie",
                        "price": 2000,
                        "toko": "andrew RGB",
                        "latitude": 3.35937213897705,
                        "longitude": 99.37841033935547,
                        "url": "https://storage.googleapis.com/swalokal_store/toko/toko-2.jpg"
                    },
                    {
                        "id": 4,
                        "name": "Oishi",
                        "price": 1500,
                        "toko": "andrew RGB",
                        "latitude": 3.35937213897705,
                        "longitude": 99.37841033935547,
                        "url": "https://storage.googleapis.com/swalokal_store/toko/toko-2.jpg"
                    },
                    {
                        "id": 5,
                        "name": "Pop Mie",
                        "price": 6000,
                        "toko": "Chalil Happy",
                        "latitude": 3.35912704467773,
                        "longitude": 99.38341522216797,
                        "url": "https://storage.googleapis.com/swalokal_store/toko/toko-3.jpg"
                    },
                    {
                        "id": 6,
                        "name": "Oishi",
                        "price": 1500,
                        "toko": "Chalil Happy",
                        "latitude": 3.35912704467773,
                        "longitude": 99.38341522216797,
                        "url": "https://storage.googleapis.com/swalokal_store/toko/toko-3.jpg"
                    },
                    {
                        "id": 7,
                        "name": "Mie",
                        "price": 2000,
                        "toko": "Fadhil Bersama",
                        "latitude": 3.35598540306091,
                        "longitude": 99.38113403320312,
                        "url": "https://storage.googleapis.com/swalokal_store/toko/toko-4.jpg"
                    },
                    {
                        "id": 8,
                        "name": "Pop Mie",
                        "price": 6500,
                        "toko": "Kedai Atjeh",
                        "latitude": 3.5628282138769407,
                        "longitude": 98.71546571100734,
                        "url": "https://storage.googleapis.com/swalokal_store/toko/toko-2.jpg"
                    },
                    {
                        "id": 10,
                        "name": "Pop Mie",
                        "price": 6000,
                        "toko": "Kedai Lajang",
                        "latitude": 3.5643062170422586,
                        "longitude": 98.71618024136616,
                        "url": "https://storage.googleapis.com/swalokal_store/toko/toko-3.jpg"
                    }
                ]
            }
        """.trimIndent()
}
