// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:2
package controllers {

  // @LINE:2
  class ReverseSwaggerController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:2
    def docs: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "swagger.json")
    }
  
  }

  // @LINE:5
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public/swagger"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "docs/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }

  // @LINE:8
  class ReverseProfissionalController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def atualizar(id:Long): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + "profissionais/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:12
    def excluirLogicamente(id:Long): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "profissionais/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:8
    def listarProfissionais(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "profissionais")
    }
  
    // @LINE:10
    def salvar(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "profissionais")
    }
  
    // @LINE:9
    def buscarPorId(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "profissionais/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
  }

  // @LINE:15
  class ReverseContatoController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def atualizar(id:Long): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + "contatos/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:15
    def listarContatos(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "contatos")
    }
  
    // @LINE:17
    def salvar(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "contatos")
    }
  
    // @LINE:19
    def excluir(id:Long): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "contatos/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:16
    def buscarPorId(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "contatos/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
  }


}
