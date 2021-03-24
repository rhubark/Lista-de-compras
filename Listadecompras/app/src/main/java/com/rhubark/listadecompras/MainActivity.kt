package com.rhubark.listadecompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Implementação do adaptador
        val produtoAdapter = ArrayAdapter<String>(
            this, android.R.layout.simple_list_item_1
        )

        //Definindo o adaptador na lista
        list_view_produtos.adapter = produtoAdapter

        //defino o ouvinte do botão
        btn_inserir.setOnClickListener{

            //pego o valor digitado pelo usuario
            val produto = txt_produto.text.toString()


            //verifica se o usuario digitou algo
            if(produto.isNotEmpty()) {
                //envio o item para a lista
                produtoAdapter.add(produto)

                //limpa a caixa de texto
                txt_produto.text.clear()
            }else{
                txt_produto.error = "Preencha com algum valor."
            }

            list_view_produtos.setOnItemLongClickListener { adapterView: AdapterView<*>, view: View, position: Int, id: Long ->

                //Busca o item clicado
                val item = produtoAdapter.getItem(position)

                //removendo o item clicado
                produtoAdapter.remove(item)

                //retorno indicando se o click foi realizado
                true
            }

        }
    }
}