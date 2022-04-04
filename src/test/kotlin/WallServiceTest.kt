import org.junit.Test
import org.junit.Assert.*

class WallServiceTest {

    private val service = WallService

    @Test
    fun updateSuccessful() {
        service.add(Post(text = "первый пост"))
        service.add(Post(text = "второй пост"))
        service.add(Post(text = "третий пост"))
        val result = service.update(Post(id = 1, text = "обновленный пост"))
        assertTrue(result)
    }

    @Test
    fun updateNotSuccessful() {
        service.cleanWallservice()
        service.add(Post(text = "первый пост"))
        service.add(Post(text = "второй пост"))
        service.add(Post(text = "третий пост"))
        val result = service.update(Post(id = 4, text = "обновленный пост"))
        assertFalse(result)
    }

    @Test
    fun addIdNotNull() {
        service.cleanWallservice()
        val post = Post(text = "новый пост")
        val result = service.add(post)
        assertNotEquals(result.id, post.id)
    }

    //    @Test
//    fun updateSuccessful() {
//        val service = WallService()
//        service.add(Post(text = "первый пост"))
//        service.add(Post(text = "второй пост"))
//        service.add(Post(text = "третий пост"))
//        val result = service.update(Post(id = 1, text = "обновленный пост"))
//        assertTrue(result)
//    }
//
//    @Test
//    fun updateNotSuccessful() {
//        val service = WallService()
//        service.add(Post(text = "первый пост"))
//        service.add(Post(text = "второй пост"))
//        service.add(Post(text = "третий пост"))
//        val result = service.update(Post(id = 4, text = "обновленный пост"))
//        assertFalse(result)
//    }
//
//    @Test
//    fun addIdNotNull() {
//        val service = WallService()
//        val post = Post(text = "новый пост")
//        val result = service.add(post)
//        assertNotEquals(result.id, post.id)
//    }

}