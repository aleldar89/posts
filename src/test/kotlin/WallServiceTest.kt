import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {

    @Test
    fun addIdNotNull() {
        val service = WallService
        val result = service.add(Post(text = "первый пост"))
        assertNotNull(result)
    }

    @Test
    fun updateSuccessful() {
        val service = WallService
        service.add(Post(text = "первый пост"))
        service.add(Post(text = "второй пост"))
        service.add(Post(text = "третий пост"))
        val result = service.update(Post(id = 1, text = "обновленный пост"))
        assertTrue(result)
    }

    @Test
    fun updateNotSuccessful() {
        val service = WallService
        service.add(Post(text = "первый пост"))
        service.add(Post(text = "второй пост"))
        service.add(Post(text = "третий пост"))
        val result = service.update(Post(id = 4, text = "обновленный пост"))
        assertFalse(result)
    }

}