package com.example.conductor.controllers.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

import com.bluelinelabs.conductor.Controller

abstract class BaseController<T : ViewDataBinding > : Controller {

    protected val title: String?
        get() = null

    protected constructor() : super() {}
    protected constructor(args: Bundle) : super(args) {}

    protected lateinit var binding: T

    override fun onAttach(view: View) {
        setTitle()
        super.onAttach(view)
    }

    protected fun setTitle() {
        var parentController = parentController
        while (parentController != null) {
            if (parentController is BaseController<*> && parentController.title != null) {
                return
            }
            parentController = parentController.parentController
        }
    }

    protected abstract fun inflateView(inflater: LayoutInflater, container: ViewGroup): T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        binding = inflateView(inflater, container)
        onViewBound(binding.root)
        return binding.root
    }

    protected open fun onViewBound(view: View) {

    }
}
