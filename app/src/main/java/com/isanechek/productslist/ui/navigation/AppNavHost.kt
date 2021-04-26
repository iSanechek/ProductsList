package com.isanechek.productslist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.isanechek.productslist.ui.screens.basket.BasketScreen
import com.isanechek.productslist.ui.screens.detail.DetailScreen
import com.isanechek.productslist.ui.screens.main.MainScreen
import com.isanechek.productslist.ui.theme.ProductsListTheme
/**
 * Гугловский адок, но решил использовать все стандартное
 */
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    ProductsListTheme {
        NavHost(navController = navController, startDestination = Screens.MAIN_SCREEN) {
            composable(Screens.MAIN_SCREEN) {
                MainScreen(openDetail = {productId ->
                    navController.navigate(Screens.DETAIL_SCREEN + "/$productId")
                }, openBasket = {
                    navController.navigate(Screens.BASKET_SCREEN)
                })
            }

            composable(Screens.BASKET_SCREEN) {
                BasketScreen {
                    navController.navigateUp()
                }
            }

            composable(
                Screens.DETAIL_SCREEN + "/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.StringType })
            ) { backStackEntry ->
                val arguments = requireNotNull(backStackEntry.arguments) { "Arguments is not be null!" }
                DetailScreen(productId = arguments.getString("productId")) {
                    navController.navigateUp()
                }
            }
        }
    }
}