object WallService {

    private var posts = emptyArray<Post>()
    private var id = 1

    fun add(post: Post): Post {
        if (posts.isEmpty()) {
            posts += post.copy(id = id)
        } else {
            posts += post.copy(id = posts.last().id + 1)
            id++
        }
        return posts.last()
    }

    fun update(post: Post): Boolean {
        var answer = false
        for ((index, savedPost) in posts.withIndex())
            if (savedPost.id == post.id) {
                posts[index] = post.copy(
                    ownerId = savedPost.ownerId,
                    fromId = savedPost.fromId,
                    createdBy = savedPost.createdBy,
                    text = savedPost.text,
                    replyOwnerId = savedPost.replyOwnerId,
                    replyPostId = savedPost.replyPostId,
                    friendsOnly = savedPost.friendsOnly,
                    postType = savedPost.postType,
                    attachments = savedPost.attachments,
                    signerId = savedPost.signerId,
                    canPin = savedPost.canPin,
                    markedAsAds = savedPost.markedAsAds,
                    isFavorite = savedPost.isFavorite,
                    postponedId = savedPost.postponedId,
                )
                answer = true
            }
        return answer
    }

    fun cleanWallservice() {
        posts = emptyArray()
        id = 1
    }

}