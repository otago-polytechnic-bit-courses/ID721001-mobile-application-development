package op.mobile.app.dev.login.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import op.mobile.app.dev.login.databinding.RecyclerViewItemBinding
import op.mobile.app.dev.login.model.Login

class LoginAdapter :
    ListAdapter<Login, LoginViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<Login>() {
        override fun areItemsTheSame(
            oldItem: Login,
            newItem: Login
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Login,
            newItem: Login
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LoginViewHolder {
        return LoginViewHolder(
            RecyclerViewItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(
        holder: LoginViewHolder,
        position: Int
    ) {
        val loginDetail = getItem(position)
        holder.bind(loginDetail)
    }
}