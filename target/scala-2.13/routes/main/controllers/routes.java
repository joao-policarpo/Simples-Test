// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseSwaggerController SwaggerController = new controllers.ReverseSwaggerController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseProfissionalController ProfissionalController = new controllers.ReverseProfissionalController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseContatoController ContatoController = new controllers.ReverseContatoController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseSwaggerController SwaggerController = new controllers.javascript.ReverseSwaggerController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseProfissionalController ProfissionalController = new controllers.javascript.ReverseProfissionalController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseContatoController ContatoController = new controllers.javascript.ReverseContatoController(RoutesPrefix.byNamePrefix());
  }

}
