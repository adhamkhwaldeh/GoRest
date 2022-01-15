package com.aljawad.sons.goRestCore.viewModels.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aljawad.sons.dtos.models.UserModel
import com.aljawad.sons.goRestCore.databinding.UserListItemBinding

//@Inject
class UserPagingAdapter :
    PagingDataAdapter<UserModel, RecyclerView.ViewHolder>(UserModel.diffUtil) {

    companion object {
        private const val TYPE_ATTENDANCE = 0
        private const val TYPE_SEPARATOR = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AttendanceDataViewHolder(
            UserListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
//        return if (viewType == TYPE_LOCATION) LocationDataViewHolder(
//                AttendanceListItemBinding.inflate(
//                        LayoutInflater.from(parent.context), parent, false
//                )
//        )
//        else LocationSeparatorViewHolder(
//                ItemSeparatorLocationBinding.inflate(
//                        LayoutInflater.from(parent.context), parent, false
//                )
//        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val item = getItem(position)
//        if (item is LocationModel.LocationData)
//            (holder as LocationDataViewHolder).bind(item)
        val item = getItem(position)
        (holder as AttendanceDataViewHolder).bind(item!!)
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE_ATTENDANCE
//        if (getItem(position) is LocationModel.LocationData) TYPE_LOCATION
//        else TYPE_SEPARATOR
    }

    inner class AttendanceDataViewHolder(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserModel) = with(binding) {
            this.item = item
        }
    }

//    inner class LocationSeparatorViewHolder(private val binding: ItemSeparatorLocationBinding) :
//            RecyclerView.ViewHolder(binding.root)

//    object LocationComparator : DiffUtil.ItemCallback<LocationModel>() {
//        override fun areItemsTheSame(oldItem: LocationModel, newItem: LocationModel): Boolean {
//            val isSameLocationData = oldItem is LocationModel.LocationData
//                    && newItem is LocationModel.LocationData
//                    && oldItem.id == newItem.id
//
//            val isSameSeparator = oldItem is LocationModel.LocationSeparator
//                    && newItem is LocationModel.LocationSeparator
//                    && oldItem.tag == newItem.tag
//
//            return isSameLocationData || isSameSeparator
//        }
//
//        override fun areContentsTheSame(oldItem: LocationModel, newItem: LocationModel) =
//                oldItem == newItem
//    }


}