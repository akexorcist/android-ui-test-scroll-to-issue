package com.akexorcist.uitest.issue.scrollto

import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.akexorcist.uitest.issue.scrollto.screen.DetailScreen
import com.akexorcist.uitest.issue.scrollto.screen.MainScreen
import com.akexorcist.uitest.issue.scrollto.screen.clickAtDetailType
import com.akexorcist.uitest.issue.scrollto.screen.clickAtItemType
import com.akexorcist.uitest.issue.scrollto.screen.verifyRequestText
import com.akexorcist.uitest.issue.scrollto.ui.main.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainFlowTest : TestCase(Kaspresso.Builder.simple()) {
    private val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Test
    fun shouldGoToDetailScreen() {
        init {
            launchActivity<MainActivity>(
                MainActivity.newIntent(context = InstrumentationRegistry.getInstrumentation().targetContext)
            )
        }.run {
            step("Click at 'Item A'") {
                MainScreen {
                    clickAtItemType("Item A")
                }
            }
            step("Verify 'Item A' on detail screen") {
                DetailScreen {
                    verifyRequestText("Item A")
                }
            }
            step("Back to main screen") {
                uiDevice.pressBack()
            }
            step("Click at 'Coupon 3'") {
                MainScreen {
                    clickAtDetailType("Coupon 3")
                }
            }
            step("Verify 'Coupon 3' on detail screen") {
                DetailScreen {
                    verifyRequestText("Coupon 3")
                }
            }
            step("Back to main screen") {
                uiDevice.pressBack()
            }
            step("Click at 'Menu 8'") {
                MainScreen {
                    clickAtDetailType("Menu 8")
                }
            }
            step("Verify 'Menu 8' on detail screen") {
                DetailScreen {
                    verifyRequestText("Menu 8")
                }
            }
        }
    }
}
