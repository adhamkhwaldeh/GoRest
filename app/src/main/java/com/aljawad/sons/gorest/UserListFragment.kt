package com.aljawad.sons.gorest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.paging.LoadState
import com.aljawad.sons.goRestCore.viewModels.UseViewModel
import com.aljawad.sons.goRestCore.viewModels.adapters.UserPagingAdapter
import com.aljawad.sons.gorest.databinding.FragmentUserListBinding
import com.aljawad.sons.gorestrepository.paging.adapters.PagingLoadStateAdapter
import com.aljawad.sons.mainlibrary.extensions.launchOnLifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val userViewModel: UseViewModel by activityViewModels()

    private val userAdapter: UserPagingAdapter by lazy { UserPagingAdapter() }

    private lateinit var binding: FragmentUserListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }

        userViewModel.getUserList()
        bindVM()

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