import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    private val firstPost = Post(id = 1, ownerId = 1, text = "первый пост")
    private val secondPost = Post(id = 2, ownerId = 2, text = "второй пост")
    private val thirdPost = Post(id = 3, ownerId = 3, text = "третий пост")

    @Test
    fun addIdNotNull() {
        val service = WallService
        val result = service.add(firstPost)
        assertNotNull(result)
    }

    @Test
    fun updateSuccessful() {
        val service = WallService
        service.add(firstPost)
        service.add(secondPost)
        service.add(thirdPost)
        val result = service.update(Post(id = 1, ownerId = 100, text = "обновленный пост"))
        assertTrue(result)
    }

    @Test
    fun updateNotSuccessful() {
        val service = WallService
        service.add(firstPost)
        service.add(secondPost)
        service.add(thirdPost)
        val result = service.update(Post(id = 4, ownerId = 100, text = "обновленный пост"))
        assertFalse(result)
    }

}