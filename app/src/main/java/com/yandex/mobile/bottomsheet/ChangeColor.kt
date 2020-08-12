package com.yandex.mobile.bottomsheet

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import androidx.transition.Transition
import androidx.transition.TransitionValues

private const val propNameBackground = "com.yandex.mobile.bottomsheet:ChangeColor:background"

class ChangeColor : Transition() {

    override fun captureStartValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    override fun captureEndValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    override fun createAnimator(
        sceneRoot: ViewGroup,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        startValues ?: return null
        endValues ?: return null
        val startColor = startValues.values[propNameBackground] as? ColorDrawable ?: return null
        val endColor = endValues.values[propNameBackground] as? ColorDrawable ?: return null
        if (startColor.color == endColor.color) return null
        val view = endValues.view
        val animator = ValueAnimator.ofObject(ArgbEvaluator(), startColor.color, endColor.color)
        animator.addUpdateListener { view.setBackgroundColor(it.animatedValue as Int) }
        return animator
    }

    private fun captureValues(values: TransitionValues) {
        values.values[propNameBackground] = values.view.background
    }
}