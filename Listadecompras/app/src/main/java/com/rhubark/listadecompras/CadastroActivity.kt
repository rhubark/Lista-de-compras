package com.rhubark.listadecompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_main.*

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)


        //defino o ouvinte do botão
        btn_inserir.setOnClickListener {

            //pego o valor digitado pelo usuario
            val produto = txt_produto.text.toString()
            val qtd = txt_qtd.text.toString()
            val valor = txt_valor.text.toString()


            //verifica se o usuario digitou algo
            if (produto.isNotEmpty() && qtd.isNotEmpty() && valor.isNotEmpty()) {
                //envio o item para a lista
                val prod = Produto(
                    produto,
                    qtd.toInt(),
                    valor.toDouble()
                )

                produtosGlobal.add(prod)

                txt_produto.text.clear()
                txt_qtd.text.clear()
                txt_valor.text.clear()


                //limpa a caixa de texto
                txt_produto.text.clear()
            } else {
                txt_produto.error =
                    if (txt_produto.text.isEmpty()) "Preencha o nome do produto" else null
                txt_qtd.error = if (txt_qtd.text.isEmpty()) "Preencha uma quantidade" else null
                txt_valor.error = if (txt_valor.text.isEmpty()) "Preencha algum valor" else null
            }
        }
    }
}