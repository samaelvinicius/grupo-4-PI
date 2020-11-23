package com.projetointegrador.grupo04

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView

class TelaInicialActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        //Deve fazer a pesquisa inicial de filmes aqui

        //Exibe uma tela de progresso - TESTE
        //ProgressDialog.show(this, "Carregando", "Carregando, aguarde", true,false)

        //Animacao logotipo - 2 segundos
        findViewById<ImageView>(R.id.imgLogotipo).apply {
            alpha = 0f
            animate().alpha(1f).duration = 2000
        }
        //Animacao carga
        findViewById<ImageView>(R.id.imgCarregando) .apply {
            Handler(Looper.getMainLooper()).postDelayed({setImageResource(R.drawable.carga02)}, 800)
            Handler(Looper.getMainLooper()).postDelayed({setImageResource(R.drawable.carga03)}, 1500)
            Handler(Looper.getMainLooper()).postDelayed({setImageResource(R.drawable.carga04)}, 2300)
            Handler(Looper.getMainLooper()).postDelayed({setImageResource(R.drawable.carga05)}, 3200)
            Handler(Looper.getMainLooper()).postDelayed({setImageResource(R.drawable.carga06)}, 4500)

        }

        //Aguarda 5 segundos para abrir a tela principal
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 5000)
    }
}