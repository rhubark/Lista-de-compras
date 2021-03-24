package com.rhubark.listadecompras

import android.content.Intent
import android.icu.text.NumberFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_adicionar.setOnClickListener {
            //Criando a Intent Explicita
            val intent = Intent(this, CadastroActivity::class.java)

            //iniciando a atividade
            startActivity(intent)
        }



        //Implementação do adaptador
        val produtoAdapter = ProdutoAdapter(this)


        //Definindo o adaptador na lista
        list_view_produtos.adapter = produtoAdapter


        list_view_produtos.setOnItemLongClickListener { adapterView: AdapterView<*>, view: View, position: Int, id: Long ->

            //Busca o item clicado
            val item = produtoAdapter.getItem(position)

            //removendo o item clicado
            produtoAdapter.remove(item)

            //retorno indicando se o click foi realizado
            true


        }
    }

    @RequiresApi(Build.VERSION_CODES.N) //Documentação?
    override fun onResume() {
        super.onResume()

        val adapter = list_view_produtos.adapter as ProdutoAdapter

        adapter.clear()
        adapter.addAll(produtosGlobal)

        val soma = produtosGlobal.sumByDouble { it.valor * it.quantidade }

        val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        txt_total.text = "TOTAL: ${ f.format((soma))}"
    }
}