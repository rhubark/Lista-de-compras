package com.rhubark.listadecompras

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_main.*

class CadastroActivity : AppCompatActivity() {

    val COD_IMAGE = 42
    var imageBitMap: Bitmap? = null

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
                    valor.toDouble(),
                    imageBitMap
                )

                produtosGlobal.add(prod)

                txt_produto.text.clear()
                txt_qtd.text.clear()
                txt_valor.text.clear()

                //limpa a caixa de texto
                txt_produto.text.clear()

            } else {
                txt_produto.error = if (txt_produto.text.isEmpty()) "Preencha o nome do produto" else null
                txt_qtd.error = if (txt_qtd.text.isEmpty()) "Preencha uma quantidade" else null
                txt_valor.error = if (txt_valor.text.isEmpty()) "Preencha algum valor" else null
            }
        }

        img_foto_produto.setOnClickListener{
            abrirGaleria()
        }

    }

    fun abrirGaleria() {

        //definindo a ação do conteudo
        val intent = Intent(Intent.ACTION_GET_CONTENT)

        //definindo filtro para imagens
        intent.type = "image/*"

        //iniciando a activity com resultado
        startActivityForResult(Intent.createChooser(intent, "Escolha uma imagem"), COD_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == COD_IMAGE && resultCode == Activity.RESULT_OK){
            if (data != null) {
                val inputStream = contentResolver.openInputStream(data.getData()!!);

                imageBitMap = BitmapFactory.decodeStream(inputStream)

                img_foto_produto.setImageBitmap(imageBitMap)
            }
        }
    }

}