package op.mobile.app.dev.login.login

import androidx.recyclerview.widget.RecyclerView
import op.mobile.app.dev.login.databinding.RecyclerViewItemBinding
import op.mobile.app.dev.login.model.Login

class LoginViewHolder(private var binding: RecyclerViewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(login: Login) {
        binding.login = login
        binding.executePendingBindings()
    }
}