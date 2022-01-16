package com.aljawad.sons.goRestCore.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import br.com.ilhasoft.support.validation.Validator
import com.aljawad.sons.dtos.enums.GenderEnum
import com.aljawad.sons.dtos.enums.StatusEnum
import com.aljawad.sons.dtos.models.UserModel
import com.aljawad.sons.goRestCore.R
import com.aljawad.sons.goRestCore.databinding.DialogCreateUserBinding
import com.aljawad.sons.goRestCore.viewModels.UseViewModel
import com.aljawad.sons.mainlibrary.dialogs.ProgressDialog
import com.aljawad.sons.mainlibrary.states.BaseState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CreateUserDialog : DialogFragment() {
    companion object {
        const val CreateUserDialog_Tag = "CreateUserDialog_Tag"
    }

    lateinit var binding: DialogCreateUserBinding

    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog.newInstance(message = getString(R.string.creatingANewUser))
    }

    private val userViewModel: UseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogCreateUserBinding.inflate(inflater, container, false)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return binding.root
    }

    private fun onUserCreate() {
        lifecycleScope.launchWhenResumed {
            userViewModel.userCreateFlow.collect {
                when (it) {
                    is BaseState.Loading -> {
                        progressDialog.showDialog(childFragmentManager)
                    }
                    is BaseState.LoadingDismiss -> {
                        ProgressDialog.closeDialog(childFragmentManager)
                    }
                    is BaseState.InternalServerError -> {
                        Snackbar.make(
                            binding.root,
                            it.message ?: getText(R.string.unexpectedErrorHappened),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    is BaseState.NoAuthorized -> {
                        Snackbar.make(
                            binding.root,
                            getText(R.string.unAuthorized),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    is BaseState.NoInternetError -> {
                        Snackbar.make(
                            binding.root,
                            getText(R.string.checkYourInternetConnection),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    is BaseState.Conflict -> {
                        Snackbar.make(
                            binding.root,
                            it.message ?: getText(R.string.dataIssue),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    is BaseState.NotDataFound -> {

                    }
                    is BaseState.FeaturedState -> {
                        userViewModel.refreshWithCreate.value =
                            !(userViewModel.refreshWithCreate.value ?: false)
                        dismissAllowingStateLoss()
                    }
                }

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userModel = UserModel()
        binding.submitBtn.setOnClickListener {
            if (Validator(binding).validate()) {
                userViewModel.createUser(binding.userModel!!)
                onUserCreate()
            }
        }

        binding.genderGroup.setOnCheckedChangeListener { group, itemId ->
            when (itemId) {
                R.id.maleRadioBtn -> {
                    binding.userModel?.gender = GenderEnum.MALE.gender
                }
                R.id.femaleRadioBtn -> {
                    binding.userModel?.gender = GenderEnum.FEMALE.gender
                }
            }
            binding.userModel?.notifyChange()
        }

        binding.statusGroup.setOnCheckedChangeListener { group, itemId ->
            when (itemId) {
                R.id.activeRadioBtn -> {
                    binding.userModel?.status = StatusEnum.ACTIVE.status
                }
                R.id.inActiveRadioBtn -> {
                    binding.userModel?.status = StatusEnum.INACTIVE.status
                }
            }
            binding.userModel?.notifyChange()
        }

    }

    override fun onStart() {
        if (dialog?.window != null) {
            val dm = DisplayMetrics()
            activity?.window?.windowManager?.defaultDisplay?.getMetrics(dm)
            val width = dm.widthPixels
            val height = dm.heightPixels
            dialog?.window?.setLayout(8 * width / 9, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        super.onStart()
    }

//    override fun onResume() {
//        super.onResume()
//        if (dialog != null && dialog!!.window != null) {
//            dialog!!.window?.setLayout(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            )
//            // this is to show dialog at the bottom of the screen
//            val window: Window? = dialog!!.window
//            val wlp: WindowManager.LayoutParams = window!!.attributes
//            wlp.gravity = Gravity.BOTTOM
//            wlp.height = 2 * wlp.height / 3
//
//            window.attributes = wlp
//        }
//    }

}