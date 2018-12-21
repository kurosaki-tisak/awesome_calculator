package com.devtee.awesomecalculator.feature.dialogpicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.devtee.awesomecalculator.R
import com.devtee.awesomecalculator.databinding.FragmentDialogPickerBinding
import com.devtee.awesomecalculator.di.utils.ViewModelFactory
import dagger.android.support.DaggerAppCompatDialogFragment
import java.io.Serializable
import javax.inject.Inject

class DialogPicker : DaggerAppCompatDialogFragment() {

    lateinit var binding: FragmentDialogPickerBinding
    lateinit var binder: DialogPickerBinder

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    companion object {
        const val EXTRA_DIALOG_ITEMS = "com.devtee.awesomecalculator.feature.dialogpicker.EXTRA_DIALOG_ITEMS"
        const val EXTRA_DIALOG_REVOKE = "com.devtee.awesomecalculator.feature.dialogpicker.EXTRA_DIALOG_REVOKE"

        fun newInstance(list: ArrayList<PickerItem>,
                        submit: (Pair<Int, String>) -> Unit?): DialogPicker {
            val fragment = DialogPicker()
            val bundle = Bundle()
            bundle.putSerializable(EXTRA_DIALOG_ITEMS, list)
            bundle.putSerializable(EXTRA_DIALOG_REVOKE, submit as Serializable)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dialog_picker, container, false)

        val list: ArrayList<PickerItem> = arguments?.getSerializable(EXTRA_DIALOG_ITEMS) as ArrayList<PickerItem>
        val submit = arguments?.getSerializable(EXTRA_DIALOG_REVOKE) as (Pair<Int, String>) -> Unit?

        val fromSubmit: (Pair<Int, String>) -> Unit? = { submit.invoke(it) }
        binder = DialogPickerBinder(viewModelFactory, this, binding, list, fromSubmit)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binder.bindTo(this)
    }

    override fun show(manager: FragmentManager?, tag: String?) {
        if (this.isAdded) return
        super.show(manager, tag)
    }

    fun setupPreselectedItem(preselected: String?) {
        binder.setupPreselectedItem(preselected)
    }
}
