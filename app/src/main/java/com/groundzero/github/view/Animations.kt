package com.groundzero.github.view

import android.animation.Animator
import android.view.View
import com.bartoszlipinski.viewpropertyobjectanimator.ViewPropertyObjectAnimator

fun View.objectAnimate(): ViewPropertyObjectAnimator = ViewPropertyObjectAnimator.animate(this)

fun View.toggleSideView(show: Boolean, duration: Long = 325): Animator = run {
    var length = this.width * 0.7.toFloat()

    if (!show) {
        length *= -1
    }

    objectAnimate()
        .translationXBy(length)
        .setDuration(duration)
        .get()
}