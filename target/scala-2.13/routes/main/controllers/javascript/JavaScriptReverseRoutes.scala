// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:2
package controllers.javascript {

  // @LINE:2
  class ReverseSwaggerController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:2
    def docs: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SwaggerController.docs",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "swagger.json"})
        }
      """
    )
  
  }

  // @LINE:5
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "docs/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }

  // @LINE:8
  class ReverseProfissionalController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def atualizar: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ProfissionalController.atualizar",
      """
        function(id0) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "profissionais/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:12
    def excluirLogicamente: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ProfissionalController.excluirLogicamente",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "profissionais/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:8
    def listarProfissionais: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ProfissionalController.listarProfissionais",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "profissionais"})
        }
      """
    )
  
    // @LINE:10
    def salvar: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ProfissionalController.salvar",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "profissionais"})
        }
      """
    )
  
    // @LINE:9
    def buscarPorId: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ProfissionalController.buscarPorId",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "profissionais/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
  }

  // @LINE:15
  class ReverseContatoController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def atualizar: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ContatoController.atualizar",
      """
        function(id0) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "contatos/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:15
    def listarContatos: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ContatoController.listarContatos",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "contatos"})
        }
      """
    )
  
    // @LINE:17
    def salvar: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ContatoController.salvar",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "contatos"})
        }
      """
    )
  
    // @LINE:19
    def excluir: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ContatoController.excluir",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "contatos/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:16
    def buscarPorId: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ContatoController.buscarPorId",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "contatos/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
  }


}
