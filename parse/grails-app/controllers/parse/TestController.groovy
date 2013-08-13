package parse

class TestController {

	def parseService

	def index() {

		render parseService.sendNotification("test")
	}
}
