// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:2
  SwaggerController_0: controllers.SwaggerController,
  // @LINE:5
  Assets_1: controllers.Assets,
  // @LINE:8
  ProfissionalController_2: controllers.ProfissionalController,
  // @LINE:15
  ContatoController_3: controllers.ContatoController,
  val prefix: String
) extends GeneratedRouter {

  @javax.inject.Inject()
  def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:2
    SwaggerController_0: controllers.SwaggerController,
    // @LINE:5
    Assets_1: controllers.Assets,
    // @LINE:8
    ProfissionalController_2: controllers.ProfissionalController,
    // @LINE:15
    ContatoController_3: controllers.ContatoController
  ) = this(errorHandler, SwaggerController_0, Assets_1, ProfissionalController_2, ContatoController_3, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, SwaggerController_0, Assets_1, ProfissionalController_2, ContatoController_3, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """swagger.json""", """controllers.SwaggerController.docs"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """docs/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public/swagger", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """profissionais""", """controllers.ProfissionalController.listarProfissionais(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """profissionais/""" + "$" + """id<[^/]+>""", """controllers.ProfissionalController.buscarPorId(id:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """profissionais""", """controllers.ProfissionalController.salvar(request:Request)"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """profissionais/""" + "$" + """id<[^/]+>""", """controllers.ProfissionalController.atualizar(id:Long, request:Request)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """profissionais/""" + "$" + """id<[^/]+>""", """controllers.ProfissionalController.excluirLogicamente(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """contatos""", """controllers.ContatoController.listarContatos(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """contatos/""" + "$" + """id<[^/]+>""", """controllers.ContatoController.buscarPorId(id:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """contatos""", """controllers.ContatoController.salvar(request:Request)"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """contatos/""" + "$" + """id<[^/]+>""", """controllers.ContatoController.atualizar(id:Long, request:Request)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """contatos/""" + "$" + """id<[^/]+>""", """controllers.ContatoController.excluir(id:Long)"""),
    Nil
  ).foldLeft(Seq.empty[(String, String, String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String, String, String)]
    case l => s ++ l.asInstanceOf[List[(String, String, String)]]
  }}


  // @LINE:2
  private[this] lazy val controllers_SwaggerController_docs0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("swagger.json")))
  )
  private[this] lazy val controllers_SwaggerController_docs0_invoker = createInvoker(
    SwaggerController_0.docs,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SwaggerController",
      "docs",
      Nil,
      "GET",
      this.prefix + """swagger.json""",
      """ Swagger""",
      Seq()
    )
  )

  // @LINE:5
  private[this] lazy val controllers_Assets_versioned1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("docs/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned1_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """docs/""" + "$" + """file<.+>""",
      """ Interface Swagger UI embutida""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_ProfissionalController_listarProfissionais2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("profissionais")))
  )
  private[this] lazy val controllers_ProfissionalController_listarProfissionais2_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      ProfissionalController_2.listarProfissionais(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ProfissionalController",
      "listarProfissionais",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """profissionais""",
      """ Profissionais""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_ProfissionalController_buscarPorId3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("profissionais/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ProfissionalController_buscarPorId3_invoker = createInvoker(
    ProfissionalController_2.buscarPorId(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ProfissionalController",
      "buscarPorId",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """profissionais/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_ProfissionalController_salvar4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("profissionais")))
  )
  private[this] lazy val controllers_ProfissionalController_salvar4_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      ProfissionalController_2.salvar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ProfissionalController",
      "salvar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """profissionais""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_ProfissionalController_atualizar5_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("profissionais/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ProfissionalController_atualizar5_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      ProfissionalController_2.atualizar(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ProfissionalController",
      "atualizar",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "PUT",
      this.prefix + """profissionais/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_ProfissionalController_excluirLogicamente6_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("profissionais/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ProfissionalController_excluirLogicamente6_invoker = createInvoker(
    ProfissionalController_2.excluirLogicamente(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ProfissionalController",
      "excluirLogicamente",
      Seq(classOf[Long]),
      "DELETE",
      this.prefix + """profissionais/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private[this] lazy val controllers_ContatoController_listarContatos7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("contatos")))
  )
  private[this] lazy val controllers_ContatoController_listarContatos7_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      ContatoController_3.listarContatos(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ContatoController",
      "listarContatos",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """contatos""",
      """ Contatos""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_ContatoController_buscarPorId8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("contatos/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ContatoController_buscarPorId8_invoker = createInvoker(
    ContatoController_3.buscarPorId(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ContatoController",
      "buscarPorId",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """contatos/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_ContatoController_salvar9_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("contatos")))
  )
  private[this] lazy val controllers_ContatoController_salvar9_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      ContatoController_3.salvar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ContatoController",
      "salvar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """contatos""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private[this] lazy val controllers_ContatoController_atualizar10_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("contatos/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ContatoController_atualizar10_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      ContatoController_3.atualizar(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ContatoController",
      "atualizar",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "PUT",
      this.prefix + """contatos/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:19
  private[this] lazy val controllers_ContatoController_excluir11_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("contatos/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ContatoController_excluir11_invoker = createInvoker(
    ContatoController_3.excluir(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ContatoController",
      "excluir",
      Seq(classOf[Long]),
      "DELETE",
      this.prefix + """contatos/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:2
    case controllers_SwaggerController_docs0_route(params@_) =>
      call { 
        controllers_SwaggerController_docs0_invoker.call(SwaggerController_0.docs)
      }
  
    // @LINE:5
    case controllers_Assets_versioned1_route(params@_) =>
      call(Param[String]("path", Right("/public/swagger")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned1_invoker.call(Assets_1.versioned(path, file))
      }
  
    // @LINE:8
    case controllers_ProfissionalController_listarProfissionais2_route(params@_) =>
      call { 
        controllers_ProfissionalController_listarProfissionais2_invoker.call(
          req => ProfissionalController_2.listarProfissionais(req))
      }
  
    // @LINE:9
    case controllers_ProfissionalController_buscarPorId3_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ProfissionalController_buscarPorId3_invoker.call(ProfissionalController_2.buscarPorId(id))
      }
  
    // @LINE:10
    case controllers_ProfissionalController_salvar4_route(params@_) =>
      call { 
        controllers_ProfissionalController_salvar4_invoker.call(
          req => ProfissionalController_2.salvar(req))
      }
  
    // @LINE:11
    case controllers_ProfissionalController_atualizar5_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ProfissionalController_atualizar5_invoker.call(
          req => ProfissionalController_2.atualizar(id, req))
      }
  
    // @LINE:12
    case controllers_ProfissionalController_excluirLogicamente6_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ProfissionalController_excluirLogicamente6_invoker.call(ProfissionalController_2.excluirLogicamente(id))
      }
  
    // @LINE:15
    case controllers_ContatoController_listarContatos7_route(params@_) =>
      call { 
        controllers_ContatoController_listarContatos7_invoker.call(
          req => ContatoController_3.listarContatos(req))
      }
  
    // @LINE:16
    case controllers_ContatoController_buscarPorId8_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ContatoController_buscarPorId8_invoker.call(ContatoController_3.buscarPorId(id))
      }
  
    // @LINE:17
    case controllers_ContatoController_salvar9_route(params@_) =>
      call { 
        controllers_ContatoController_salvar9_invoker.call(
          req => ContatoController_3.salvar(req))
      }
  
    // @LINE:18
    case controllers_ContatoController_atualizar10_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ContatoController_atualizar10_invoker.call(
          req => ContatoController_3.atualizar(id, req))
      }
  
    // @LINE:19
    case controllers_ContatoController_excluir11_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ContatoController_excluir11_invoker.call(ContatoController_3.excluir(id))
      }
  }
}
