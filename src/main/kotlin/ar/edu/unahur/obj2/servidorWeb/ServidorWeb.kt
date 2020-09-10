package ar.edu.unahur.obj2.servidorWeb

enum class CodigoHttp(val codigo: Int) {
  OK(200),
  NOT_IMPLEMENTED(501),
  NOT_FOUND(404),
}

class ServidorWeb {
  val modulos = mutableListOf<Modulo>()
  val analizadores = mutableListOf<Analizadores>()

  fun realizarPedido(ip: String, url: String, fechaHora: Int): Respuesta {
    if (!url.startsWith("http:")) {
      return Respuesta(codigo = CodigoHttp.NOT_IMPLEMENTED, body = "", tiempo = 10)
    }

    if (this.algunModuloSoporta(url)) {
      val moduloSeleccionado = this.modulos.find { it.puedeTrabajarCon(url) }!!
      return Respuesta(CodigoHttp.OK, moduloSeleccionado.body, moduloSeleccionado.tiempoRespuesta)
    }

    return Respuesta(codigo = CodigoHttp.NOT_FOUND, body = "", tiempo = 10)
  }

  fun algunModuloSoporta(url: String) = this.modulos.any { it.puedeTrabajarCon(url) }

  fun agregarModulo(modulo: Modulo) {
    this.modulos.add(modulo)
  }

  fun agregarAnalizador(analizador: Analizadores) =  this.analizadores.add(analizador)
  fun quitarAnalizador(analizador: Analizadores)  = this.analizadores.remove(analizador)
}

class Respuesta(val codigo: CodigoHttp, val body: String, val tiempo: Int){}


/*class AnalizadorDeDemoras(val demoraMinima: Int ) : Analizador(){
  val respuestasDemoradas = mutableListOf<Respuesta>()
  override fun analizar(respuesta: Respuesta) {
    if (respuesta.tiempo > demoraMinima) {
        respuestasDemoradas.add(respuesta)
    }


  }
}*/
