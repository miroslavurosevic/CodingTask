import java.util.TreeSet;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;


public class Main {

	public static void main(String[] args) {
		TreeSet<MyEntity> entityList = new TreeSet<MyEntity>();
		entityList.add(new MyEntity("entity1"));
		entityList.add(new MyEntity("entity2"));
		entityList.add(new MyEntity("entity3"));

		//Server
		Vertx vertx = Vertx.vertx();
		HttpServer server = vertx.createHttpServer();
		Router router = Router.router(vertx);
		
		//List all entities
		router.get("/entities").handler(r-> {
			r.response().putHeader("content-type", "application/json; charset=utf-8")
			.end(Json.encodePrettily(entityList));
		});
		
		//Read single entity specified by id
		router.get("/entities/:id").handler(r-> {
			for(MyEntity entity: entityList) {
				if(entity.getID().equals(r.request().getParam("id"))) {
					r.response().putHeader("content-type", "application/json; charset=utf-8")
					.end(Json.encodePrettily(entity));
				}
			}
		});
		
		router.route("/entities*").handler(BodyHandler.create());
		
		//Add new entity
		router.post("/entities").handler(r->{
			
			try {
				final MyEntity entity = Json.decodeValue(r.getBodyAsString(),MyEntity.class);
				entityList.add(entity);
				r.response().end("New entity added");
			} catch (Exception e) {
				r.response().end(e.toString());
			}
		});
		
		//Add new sub-entity to entity specified by id
		router.post("/entities/:id").handler(r-> {
			final MyEntity subEntity = Json.decodeValue(r.getBodyAsString(),MyEntity.class);
			String id = r.request().getParam("id");
			
			for(MyEntity entity: entityList) {
				if(entity.getID().equals(id)) {
					entity.getSubEntities().add(subEntity);
				}
			}
			r.response().end("Subentity added");	
		});
		
		server.requestHandler(router).listen(7777);

	}


}
