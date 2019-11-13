package com.example.conductor.controllers

import android.annotation.TargetApi
import android.os.Build.VERSION_CODES
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.conductor.R
import com.example.conductor.controllers.base.BaseController
import com.example.conductor.databinding.ControllerDragDismissBinding
import com.example.conductor.utils.ScaleFadeChangeHandler
import com.example.conductor.widget.ElasticDragDismissFrameLayout

@TargetApi(VERSION_CODES.LOLLIPOP)
class DragDismissController : BaseController<ControllerDragDismissBinding>() {

    private val dragDismissListener =
        object : ElasticDragDismissFrameLayout.ElasticDragDismissCallback() {
            override fun onDragDismissed() {
                overridePopHandler(ScaleFadeChangeHandler())
                router.popController(this@DragDismissController)
            }
        }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup
    ): ControllerDragDismissBinding {
        return DataBindingUtil.inflate(inflater,
            R.layout.controller_drag_dismiss, container, false)
    }

    override fun onViewBound(view: View) {
        (view as ElasticDragDismissFrameLayout).addListener(dragDismissListener)
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)

        (view as ElasticDragDismissFrameLayout).removeListener(dragDismissListener)
    }
}
