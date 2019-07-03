package status

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import org.apache.spark.{SparkContext, SparkConf}

class PostStatus @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

    def postStatus = Action { implicit request =>
        val json = request.body.asJson
        val status = MetroStatus(json.get("position").as[Int],
                                json.get("direction").as[Boolean],
                                json.get("passengers").as[Int],
                                json.get("battery").as[Int],
                                json.get("speed").as[Int],
                                json.get("temperature").as[Int],
                                json.get("id").as[Int])
        History.add(status)
        Ok("Success")
    }

}
