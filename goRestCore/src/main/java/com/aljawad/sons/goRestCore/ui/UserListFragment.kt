package com.aljawad.sons.goRestCore.ui

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.aljawad.sons.goRestCore.R
import com.aljawad.sons.goRestCore.viewModels.UseViewModel
import com.aljawad.sons.goRestCore.adapters.UserPagingAdapter
import com.aljawad.sons.goRestCore.databinding.FragmentUserListBinding
import com.aljawad.sons.gorestrepository.paging.adapters.PagingLoadStateAdapter
import com.aljawad.sons.mainlibrary.extensions.launchOnLifecycleScope
import com.aljawad.sons.mainlibrary.extensions.observe
import com.aljawad.sons.mainlibrary.states.BaseState
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import java.nio.file.Files.delete

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val userViewModel: UseViewModel by activityViewModels()

    private val userAdapter: UserPagingAdapter by lazy {
        UserPagingAdapter(
            onUserDeleted = {
                MaterialAlertDialogBuilder(requireContext())
                    .setCancelable(false)
                    .setTitle(getString(R.string.delete))
                    .setMessage(R.string.doYouWantToDeleteThisUser)
                    .setNegativeButton(getString(R.string.cancel)) { dialog, which ->
                        // Respond to negative button press
                    }
                    .setPositiveButton(getString(R.string.delete)) { dialog, which ->
                        // Respond to positive button press
                        userViewModel.deleteUser(it.id!!)
                        onUserDelete()
                    }
                    .show()
            }
        )
    }

    private lateinit var binding: FragmentUserListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.getUserList()

        bindVM()

        observe(userViewModel.refreshWithFilter) {
           userAdapter.refresh()
        }

    }

    private fun onUserDelete() {
        lifecycleScope.launchWhenResumed {
            userViewModel.userDeleteFlow.collect {
                when (it) {
                    is BaseState.Loading -> {
                    }
                    is BaseState.LoadingDismiss -> {

                    }
                    is BaseState.InternalServerError -> {

                    }
                    is BaseState.NoAuthorized -> {

                    }
                    is BaseState.NoInternetError -> {

                    }
                    is BaseState.NotDataFound -> {

                    }
                    is BaseState.FeaturedState -> {
                        Snackbar.make(
                            binding.root,
                            getText(R.string.userHasBeenDeletedSuccessfully),
                            Snackbar.LENGTH_LONG
                        ).show()
                        userAdapter.refresh()
                    }
                }

            }
        }
    }

    private fun bindVM() = with(binding) {
        with(userAdapter) {
            swipeRefresh.setOnRefreshListener { userAdapter.refresh() }
            recyclerView.adapter = withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(
                    this
                ),
                footer = PagingLoadStateAdapter(
                    this
                )
            )
            with(userViewModel) {
                launchOnLifecycleScope {
                    usersFlow.collectLatest { submitData(it) }
                }
                launchOnLifecycleScope {
                    loadStateFlow.collectLatest {
                        swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                    }
                }
                launchOnLifecycleScope {
                    loadStateFlow.distinctUntilChangedBy { it.refresh }
                        .filter { it.refresh is LoadState.NotLoading }
                        .collect {
                            recyclerView.scrollToPosition(0)
//                                binding.statesLayout.FlipLayout(if (attendanceAdapter.itemCount == 0)
//                                    LayoutStatesEnum.Nodatalayout else LayoutStatesEnum.SuccessLayout)
//                                if (it.append is LoadState.Error) {
//
//                                }
                        }

                    loadStateFlow.distinctUntilChangedBy { it.refresh }.collect {

                    }
                }
            }
        }
//        observe(attendancesViewModel.refreshWithFilter) {
//            attendanceAdapter.refresh()
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
//        _binding = null
    }
}