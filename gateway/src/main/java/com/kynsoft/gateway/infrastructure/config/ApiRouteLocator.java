package com.kynsoft.gateway.infrastructure.config;
import com.kynsoft.gateway.application.dto.RouteDTO;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.net.URISyntaxException;

@AllArgsConstructor
public class ApiRouteLocator implements RouteLocator {

  private final UpdateRouteContext updateRouteContext;

  private final RouteLocatorBuilder routeLocatorBuilder;
  

  @Override
  public Flux<Route> getRoutes() {
    RouteLocatorBuilder.Builder routesBuilder = routeLocatorBuilder.routes();
    
    for (RouteDTO route : updateRouteContext.getDefinitionsContext().getDefinitions()) {

        String path = route.getUri().toString();
      URI uri;
        try {
        path =  path.replace("http","https");
          uri = new URI(path);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        routesBuilder.route(route.getName(),
                r -> r.path(route.getPath())
                        .filters(f -> f.stripPrefix(1))
                        .uri(uri));
    }
    
    return routesBuilder.build().getRoutes();
  }

}