package com.akexorcist.uitest.issue.scrollto.screen

import android.view.View
import com.akexorcist.uitest.scrollto.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object MainScreen : KScreen<MainScreen>() {
    override val layoutId: Int = R.layout.activity_main
    override val viewClass: Class<*>? = null

    val recyclerViewPrimary = KRecyclerView(
        builder = { withId(R.id.recyclerViewPrimary) },
        itemTypeBuilder = {
            itemType(MainScreen::PrimaryItemViewHolder)
            itemType(MainScreen::PrimaryDetailViewHolder)
        }
    )

    class PrimaryItemViewHolder(parent: Matcher<View>) : KRecyclerItem<PrimaryItemViewHolder>(parent) {
        val textViewLabel = KTextView(parent) { withId(R.id.textViewLabel) }
    }

    class PrimaryDetailViewHolder(parent: Matcher<View>) : KRecyclerItem<PrimaryDetailViewHolder>(parent) {
        val recyclerViewSecondary = KRecyclerView(
            parent = parent,
            builder = { withId(R.id.recyclerViewSecondary) },
            itemTypeBuilder = { itemType(MainScreen::SecondaryItemViewHolder) }
        )
    }

    class SecondaryItemViewHolder(parent: Matcher<View>) : KRecyclerItem<SecondaryItemViewHolder>(parent) {
        val buttonItem = KButton(parent) { withId(R.id.buttonItem) }
    }
}

fun MainScreen.clickAtItemType(label: String) {
    recyclerViewPrimary {
        childWith<MainScreen.PrimaryItemViewHolder> {
            withDescendant {
                withText(label)
                withId(R.id.textViewLabel)
            }
        } perform {
            textViewLabel.click()
        }
    }
}

fun MainScreen.clickAtDetailType(label: String) {
    recyclerViewPrimary {
        childWith<MainScreen.PrimaryDetailViewHolder> {
            withDescendant {
                withText(label)
                withId(R.id.buttonItem)
            }
        } perform {
            recyclerViewSecondary {
                childWith<MainScreen.SecondaryItemViewHolder> {
                    withDescendant {
                        withText(label)
                        withId(R.id.buttonItem)
                    }
                } perform {
                    buttonItem.click()
                }
            }
        }
    }
}
