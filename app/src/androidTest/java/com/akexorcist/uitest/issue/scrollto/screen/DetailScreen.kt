package com.akexorcist.uitest.issue.scrollto.screen

import com.akexorcist.uitest.scrollto.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KTextView

object DetailScreen : KScreen<DetailScreen>() {
    override val layoutId: Int = R.layout.activity_detail
    override val viewClass: Class<*>? = null

    val textViewRequest = KTextView { withId(R.id.textViewRequest) }
}

fun DetailScreen.verifyRequestText(text: String) {
    textViewRequest.hasText(text)
}
