package com.projetointegrador.grupo04.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.projetointegrador.grupo04.MainActivity
import com.projetointegrador.grupo04.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

@Suppress("UNREACHABLE_CODE")
class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)

        btnRegister2.setOnClickListener{
            requireActivity().run{
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }


    }

}