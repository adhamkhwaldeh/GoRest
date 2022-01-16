package com.aljawad.sons.goRestCore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aljawad.sons.dtos.models.UserModel
import com.aljawad.sons.goRestCore.databinding.UserListItemBinding

class UserPagingAdapter(private val onUserDeleted: (UserModel) -> Unit) :
    PagingDataAdapter<UserModel, UserPagingAdapter.UserViewHolder>(UserModel.diffUtil) {

    companion object {
        private const val TYPE_USER = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!)
        holder.itemView.setOnLongClickListener {
            onUserDeleted(item)
            return@setOnLongClickListener false
        }
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE_USER
    }

    inner class UserViewHolder(private val binding: UserListItemBinding) :
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