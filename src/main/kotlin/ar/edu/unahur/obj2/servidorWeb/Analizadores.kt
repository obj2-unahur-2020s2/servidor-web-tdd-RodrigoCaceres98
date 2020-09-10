package ar.edu.unahur.obj2.servidorWeb
abstract class Analizadores(){
    abstract fun analizar(respuesta: Respuesta)

}
class AnalizadorDeDemoras(val demoraMinima : Int) :Analizadores() {
    val respuestasDemoradas = mutableListOf<Respuesta>()
    override fun analizar(respuesta: Respuesta) {
        if (respuesta.tiempo > demoraMinima ){
            respuestasDemoradas.add(respuesta)
        }
    }
    fun cantDeMoludosDeDemoras(modulo: Modulo) = 10




}
