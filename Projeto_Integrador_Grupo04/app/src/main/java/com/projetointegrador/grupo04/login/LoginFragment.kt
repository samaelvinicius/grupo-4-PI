package com.projetointegrador.grupo04.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

        btnLogin.setOnClickListener{
            requireActivity().run{
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        btnGoogle.setOnClickListener{
            requireActivity().run{
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        btnFacebook.setOnClickListener{
            requireActivity().run{
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        val v = inflater.inflate(R.layout.fragment_register, container, false)

        val btn = v.findViewById<View>(R.id.btnRegister) as Button

        btn.setOnClickListener{
            val fragment = RegisterFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        return v

    }

}


