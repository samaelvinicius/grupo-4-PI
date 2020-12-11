package com.projetointegrador.grupo04.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.projetointegrador.grupo04.MainActivity
import com.projetointegrador.grupo04.R
import kotlinx.android.synthetic.main.fragment_login.*

@Suppress("UNREACHABLE_CODE")
class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)


        view.findViewById<Button>(R.id.btnRegister).setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }

}




