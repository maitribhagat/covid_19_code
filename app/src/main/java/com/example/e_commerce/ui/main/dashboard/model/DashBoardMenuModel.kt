package com.example.e_commerce.ui.main.dashboard.model

import java.util.*

data class DashBoardMenuModel(
    var status: String,
    var totalResults: String,
    var data: List<DashboardData>
) {
}


data class DashboardData(
    var menu: List<Menu>,
    var category: List<Category>,
    var live: List<Live>
) {}


data class Menu(
    var Id: Int,
    var menuName: String,
    var menuDescription: String,
    var menuImage: String
) {}

data class Category(
    var Id: Int,
    var categoryName: String,
    var categoryDescription: String,
    var title: String,
    var image: String,
    var description: String
) {}


data class Live( var title: String,
                     var image: String,
                     var description: String) {}