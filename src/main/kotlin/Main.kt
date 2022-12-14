import controllers.*
import controllers.maquina.EncordadoraController
import controllers.maquina.PersonalizadoraController
import controllers.usuario.ClienteController
import controllers.usuario.EncargadoController
import controllers.usuario.TrabajadorController
import database.*
import models.Pedido
import repository.adquisicion.AdquisicionRepositoryImpl
import repository.usuario.ClienteRepositoryImpl
import repository.usuario.EncargadoRepositoryImpl
import repository.encordadora.EncordadoraRepositoryImpl
import repository.encordar.EncordarRepositoryImpl
import repository.pedido.PedidoRepositoryImpl
import repository.personalizadora.PersonalizadoraRepositoryImpl
import repository.personalizar.PersonalizarRepositoryImpl
import repository.producto.ProductoRepositoryImpl
import repository.tarea.TareaRepositoryImpl
import repository.usuario.TrabajadorRepositoryImpl
import repository.turno.TurnoRepositoryImpl

/**
 * @author Sebastian Mendoza y Mario Resa
 */

/**
 * Main() Funcion principal del programa
 *
 * En el se cargan los controllers de los componentes de la aplicacion, se introducen los datos de Data y se
 * operan con ellos / Operaciones CRUD.
 *
 * @param args
 */
fun main(args: Array<String>) {

    initDataBase()

    //Controladores
    val productoController = ProductoController(ProductoRepositoryImpl())
    val adquisicionController = AdquisicionController(AdquisicionRepositoryImpl())
    val encordarController = EncordarController(EncordarRepositoryImpl())
    val personalizarController = PersonalizarController(PersonalizarRepositoryImpl())
    val encargadoController = EncargadoController(EncargadoRepositoryImpl())
    val clienteController = ClienteController(ClienteRepositoryImpl())
    val trabajadorController = TrabajadorController(TrabajadorRepositoryImpl())
    val encordadoraController = EncordadoraController(EncordadoraRepositoryImpl())
    val personalizadoraController = PersonalizadoraController(PersonalizadoraRepositoryImpl())
    val turnoController = TurnoController(TurnoRepositoryImpl())
    val tareaController = TareaController(TareaRepositoryImpl())
    val pedidoController = PedidoController(PedidoRepositoryImpl())

    //Inserci??n de datos
    val productosInit = getProductosInit()
    productosInit.forEach { producto ->
        productoController.createProducto(producto)
    }
    val adquisicionesInit = getAdquisicionInit()
    adquisicionesInit.forEach { adquisicion ->
        adquisicionController.createAdqusicion(adquisicion)
    }
    val encordadosInit = getEncordadosInit()
    encordadosInit.forEach { encordar ->
        encordarController.createEncordado(encordar)
    }
    val personalizacionesInit = getPersonalizacionInit()
    personalizacionesInit.forEach { personalizacion ->
        personalizarController.createPersonalizacion(personalizacion)
    }
    val encargadosInit = getEncargadoInit()
    encargadosInit.forEach { encargado ->
        encargadoController.createEncargado(encargado)
    }
    val raquetasInit = getRaquetasInit()
    val clientesInit = getClientesInit()
    clientesInit.forEachIndexed { index, cliente ->
        cliente.raqueta = raquetasInit[index]
    }
    clientesInit.forEach { cliente ->
        clienteController.createCliente(cliente)
    }
    val trabajadoresInit = getTrabajadorInit()
    trabajadoresInit.forEach { trabajadores ->
        trabajadorController.createTrabajador(trabajadores)
    }
    val encordadorasInit = getEncordadorasInit()
    encordadorasInit.forEach { encordadora ->
        encordadoraController.createEncordadora(encordadora)
    }
    val personalizadorasInit = getPersonalizadorasInit()
    personalizadorasInit.forEach { personalizadora ->
        personalizadoraController.createPersonalizadora(personalizadora)
    }
    val turnosInit = getTurnosInit()
    turnosInit.forEach { turno ->
        turnoController.createTurno(turno)
    }

    val tareasInit = getTareasInit()
    tareasInit.forEach { tarea ->
        tareaController.createTarea(tarea)
    }

    val pedidosInit = getPedidosInit()
    pedidosInit.forEach { pedidos ->
        pedidoController.createPedido(pedidos)

    }

    //CRUD
    //Producto
    //FindAll
    val producto = productoController.getProductos()
    producto.forEach { println(it) }
    //FindById
    val productoId = productoController.getProductoById(producto[0].id)
    println(productoId)
    //Update
    val productoUpdate = productoController.getProductoById(producto[0].id)
    productoUpdate.let {
        it.stock += 3
        productoController.updateProducto(it)
    }
    println(productoUpdate)
    //Delete
    val productoDelete = productoController.getProductoById(producto[0].id)
    productoDelete.let { if (productoController.deleteProducto(it)) println("Producto eliminado") }

    //Adquisicion
    //FindAll
    val adquisicion = adquisicionController.getAdquisiciones()
    adquisicion.forEach { println(it) }
    //FindById
    val adquisicionId = adquisicionController.getAdquisicionById(adquisicion[0].id)
    println(adquisicionId)
    //Update
    val adquisicionUpdate = adquisicionController.getAdquisicionById(adquisicion[0].id)
    adquisicionUpdate.let {
        it.cantidad += 1
        it.precio = it.precio?.times(it.cantidad)
        adquisicionController.updateAdquisicion(it)
    }
    println(adquisicionUpdate)
    //Delete
    val adquisicionDelete = adquisicionController.getAdquisicionById(adquisicion[0].id)
    adquisicionDelete.let { if (adquisicionController.deleteAdquisicion(it)) println("Adquisicion eliminada") }

    //Encordar
    //FindAll
    val encordar = encordarController.getEncordados()
    encordar.forEach { println(it) }
    //FindById
    val encordadoId = encordarController.getEncordadoById(encordar[0].id)
    println(encordadoId)
    //Update
    val encordadoUpdate = encordarController.getEncordadoById(encordar[0].id)
    encordadoUpdate.let {
        it.nudos += 2
        encordarController.updateEncordado(it)
    }
    println(encordadoUpdate)
    //Delete
    val encordadoDelete = encordarController.getEncordadoById(encordar[0].id)
    encordadoDelete.let { if (encordarController.deleteEncordado(it)) println("Encordado eliminado") }

    //Personalizar
    //FindAll
    val personalizar = personalizarController.getPersonalizaciones()
    personalizar.forEach { println(it) }
    //FindById
    val personalizarId = personalizarController.getPersonalizacionById(personalizar[0].id)
    println(personalizarId)
    //Update
    val personalizarUpdate = personalizarController.getPersonalizacionById(personalizar[0].id)
    personalizarUpdate.let {
        it.peso += 1
        personalizarController.updatePersonalizacion(personalizarUpdate)
    }
    println(personalizarUpdate)
    //Delete
    val personalizarDelete = personalizarController.getPersonalizacionById(personalizar[0].id)
    personalizarDelete.let { if (personalizarController.deletePersonalizacion(it)) println("Personalizaci??n eliminada") }

    //Encargado
    //FindAll
    val encargado = encargadoController.getEncargados()
    encargado.forEach { println(it) }
    //FindById
    val encargadoId = encargadoController.getEncargadoById(encargado[0].id)
    println(encargadoId)
    //Update
    val encargadoUpdate = encargadoController.getEncargadoById(encargado[0].id)
    encargadoUpdate.let {
        it.nombre = "Ernesto"
        encargadoController.updateEncargado(it)
    }
    println(encargadoUpdate)
    //Delete
    val encargadoDelete = encargadoController.getEncargadoById(encargado[0].id)
    encargadoDelete.let { if (encargadoController.deleteEncargado(it)) println("Encargado eliminado") }

    //Cliente
    //FindAll
    val cliente = clienteController.getClientes()
    cliente.forEach { println(it) }
    //FindById
    val clienteId = clienteController.getClienteById(cliente[0].id)
    println(clienteId)
    //Update
    val clienteUpdate = clienteController.getClienteById(cliente[0].id)
    clienteUpdate.let {
        it.apellido = "Acosta"
        clienteController.updateCliente(it)
    }
    println(clienteUpdate)
    //Delete
    val clienteDelete = clienteController.getClienteById(cliente[1].id)
    clienteDelete.let { if (clienteController.deleteCliente(it)) println("Cliente eliminado") }

    //Pedido
    //FindAll
    val pedidos = pedidoController.getPedidos()
    pedidos.forEach { println(it) }
    //FindById
    val pedidoId = pedidoController.getPedidoById(pedidos[0].id)
    println(pedidoId)
    //Update
    val pedidoUpdate = pedidoController.getPedidoById(pedidos[0].id)
    pedidoUpdate.let {
        it.estado = Pedido.TipoEstado.PROCESANDO
        pedidoController.updatePedido(it)
    }
    println(pedidoUpdate)
    //Delete
    val pedidoDelete = pedidoController.getPedidoById(pedidos[1].id)
    pedidoDelete.let { if (pedidoController.deletePedido(it)) println("Pedido eliminado") }

    //Trabajador
    //FindAll
    val trabajador = trabajadorController.getTrabajadores()
    trabajador.forEach { println(it) }
    //FindById
    val trabajadorId = trabajadorController.getTrabajadorById(trabajador[0].id)
    println(trabajadorId)
    //Update
    val trabajadorUpdate = trabajadorController.getTrabajadorById(trabajador[0].id)
    trabajadorUpdate.let {
        it.email = "emailCambiado@otroemail.com"
        trabajadorController.updateTrabajador(it)
    }
    println(trabajadorUpdate)
    //Delete
    val trabajadorDelete = trabajadorController.getTrabajadorById(trabajador[0].id)
    trabajadorDelete.let { if (trabajadorController.deleteTrabajador(it)) println("Trabajador eliminado") }

    // ENCORDADORAS
    // FindAll
    val encordadoras = encordadoraController.getEncordadoras()
    println(encordadoras)
    // FindById
    val encordadora = encordadoraController.getEncordadoraById(encordadoras[1].id)
    println(encordadora)
    // Update
    encordadora.let {
        it.isManual = false
        encordadoraController.updateEncordadora(it)
    }
    println(encordadoraController.getEncordadoras())
    // Delete
    encordadora.let {
        encordadoraController.deleteEncordadora(it)
    }
    println(encordadoraController.getEncordadoras())
    // PERSONALIZADORAS
    // FindAll
    val personalizadoras = personalizadoraController.getPersonalizadoras()
    println(personalizadoras)
    // FindById
    val personalizadora = personalizadoraController.getPersonalizadoraById(personalizadoras[1].id)
    println(personalizadoras)
    // Update
    personalizadora.let {
        it.maniobrabilidad = false
        personalizadoraController.updatePersonalizadora(it)
    }
    println(personalizadoraController.getPersonalizadoras())
    // Delete
    personalizadora.let {
        personalizadoraController.deletePersonalizadora(it)
    }
    println(personalizadoraController.getPersonalizadoras())

    // TURNOS
    // FindAll
    val turnos = turnoController.getTurnos()
    println(turnos)
    // FindById
    val turno = turnoController.getTurnoById(turnos[0].id)
    println(turno)
    // Update
    turno.let {
        it.encordadora = null
        turnoController.updateTurno(it)
    }
    println(turnoController.getTurnos())
    // Delete
    turno.let {
        turnoController.deleteTurno(it)
    }
    println(turnoController.getTurnos())

    // TAREAS
    // FindAll
    val tareas = tareaController.getTareas()
    println(tareas)
    // FindById
    val tarea = tareaController.getTareaById(tareas[0].id)
    println(tarea)
    // Update
    tarea.let {
        it.trabajador = getTrabajadorInit()[2]
        tareaController.updateTarea(it)
    }
    println(tareaController.getTareas())
    // Delete
    tarea.let {
        tareaController.deleteTarea(it)
    }
    println(tareaController.getTareas())
}

/**
 * InitDataBase(), Funcion que se encarga de cargar la configuracion de la BBDD
 *
 */
fun initDataBase() {
    HibernateManager.open()
    HibernateManager.close()
}