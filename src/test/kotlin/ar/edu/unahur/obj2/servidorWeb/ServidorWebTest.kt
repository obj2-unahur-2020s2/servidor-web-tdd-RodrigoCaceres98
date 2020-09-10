package ar.edu.unahur.obj2.servidorWeb

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class ServidorWebTest : DescribeSpec({
  describe("Un servidor web") {
    val servidor = ServidorWeb()
    val moduloTexto = Modulo(listOf("txt"), "qué linda foto", 100)
    val moduloGrafico = Modulo(listOf("jpg", "gif"), "qué linda foto", 100)
    servidor.agregarModulo(moduloGrafico)
    servidor.agregarModulo(moduloTexto)

    it("devuelve 501 si recibe un pedido que no es HTTP") {
      val respuesta = servidor.realizarPedido("207.46.13.5", "https://pepito.com.ar/hola.txt", 20)
      respuesta.codigo.shouldBe(CodigoHttp.NOT_IMPLEMENTED)
      respuesta.body.shouldBe("")
    }

    it("devuelve 200 si algún módulo puede trabajar con el pedido") {
      val respuesta = servidor.realizarPedido("207.46.13.5", "http://pepito.com.ar/hola.txt",25)
      respuesta.codigo.shouldBe(CodigoHttp.OK)
      respuesta.body.shouldBe("todo bien")
    }

    it("devuelve 404 si ningún módulo puede trabajar con el pedido") {
      val respuesta = servidor.realizarPedido("207.46.13.5", "http://pepito.com.ar/playa.png", 40)
      respuesta.codigo.shouldBe(CodigoHttp.NOT_FOUND)
      respuesta.body.shouldBe("")
    }

    it("devuelve que la respuesta esta demorada "){
      val respuesta = servidor.realizarPedido("207.46.13.5", "http://pepito.com.ar/playa.png", 20)
      val respuesta1 = servidor.realizarPedido("207.46.13.6", "https://pepito.com.ar/playa.png", 30)
      val respuesta2 = servidor.realizarPedido("207.46.13.7", "http://pepito.com.ar/playa.png", 15)
      val analizadorDeDemoras = AnalizadorDeDemoras(15)
      servidor.agregarAnalizador(analizadorDeDemoras)
      analizadorDeDemoras.analizar(respuesta)
      analizadorDeDemoras.cantDeMoludosDeDemoras(moduloTexto).shouldBe(1)





    }

  }

})

