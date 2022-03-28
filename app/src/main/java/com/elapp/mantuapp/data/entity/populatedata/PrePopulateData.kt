package com.elapp.mantuapp.data.entity.populatedata

import com.elapp.mantuapp.R
import com.elapp.mantuapp.data.entity.Category
import com.elapp.mantuapp.data.entity.Priority

class PrePopulateData {

    companion object {
        fun getPriority(): List<Priority> {
            return listOf(
                Priority(
                    priorityName = "Do-it-now",
                    priorityDesc = "Pekerjaan Yang Penting dan Mendesak",
                    priorityColor = R.color.colorRed
                ),
                Priority(
                    priorityName = "Schedule",
                    priorityDesc = "Pekerjaan yang Penting, Tapi Tidak Mendesak",
                    priorityColor = R.color.colorYellow
                ),
                Priority(
                    priorityName = "Delegate",
                    priorityDesc = "Pekerjaan yang tidak terlalu penting, Tapi Mendesak",
                    priorityColor = R.color.colorBlue
                ),
                Priority(
                    priorityName = "Drop",
                    priorityDesc = "Pekerjaan yang tidak penting dan tidak mendesak",
                    priorityColor = R.color.colorGreen
                ),
            )
        }
        fun getCategory(): List<Category> {
            return listOf(
                Category(
                    categoryName = "Belajar",
                ),
                Category(
                    categoryName = "Liburan",
                ),
                Category(
                    categoryName = "Pekerjaan Rumah",
                ),
                Category(
                    categoryName = "Sekolah",
                )
            )
        }
    }

}