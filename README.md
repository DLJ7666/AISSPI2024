<h1>VideoMiner Herramienta</h1>

VideoMiner es una herramienta de minería de datos que permite cargar, procesar y analizar información sobre creadores de contenido multimedia alojados en distintas
plataformas.Integra y almacena todos los datos en una base de
datos. Las distintas acciones que se pueden tomar son las siguientes:

<h2>Subtítulos:</h2>

    readCaption:

        GET /{channelId}/videos/{videoId}/captions/{captionId}

        Obtiene los subtítulos cuyo id es igual captionId.

    readCaptions:

        GET /{channelId}/videos/{videoId}/captions

        Obtiene todos los subtítulos del vídeo cuyo id es igual videoId.

    createCaption:

        POST /{channelId}/videos/{videoId}/captions

        Añade unos subtítulos al vídeo cuyo id es igual videoId.

    deleteCaption:

        DELETE /{channelId}/videos/{videoId}/captions/{captionId}

        Elimina los subtítulos cuyo id es igual captionId.

    updateCaption:

        PUT /{channelId}/videos/{videoId}/captions/{captionId}

        Actualiza los subtítulos cuyo id es igual captionId.

<h2>Canales:</h2>

    readChannel:

        GET /{channelId}

        Obtiene el canal cuyo id es igual channelId.

    readChannels:

        GET 

        Obtiene todos los canales.

    createChannel:

        POST 

        Crea un canal.

    deleteChannel:

        DELETE /{channelId}

        Elimina el canal  cuyo id es igual channelId.

    updateChannel:

        PUT /{channelId}

        Actualiza el canal  cuyo id es igual channelId.

<h2>Comentarios:</h2>

    readComment:

        GET /{channelId}/videos/{videoId}/comments/{commentId}

        Obtiene el comentario cuyo id es igual commentId.

    readComments:

        GET /{channelId}/videos/{videoId}/comments

        Obtiene todos los comentarios del vídeo cuyo id es igual videoId.

    createComment:

        POST /{channelId}/videos/{videoId}/comments

        Crea un comentario en el vídeo cuyo id es igual videoId.

    deleteComment:

        DELETE /{channelId}/videos/{videoId}/comments/{commentId}

        Elimina el comentario cuyo id es igual commentId.

    updateComment:

        PUT /{channelId}/videos/{videoId}/comments/{commentId}

        Actualiza el comentario cuyo id es igual commentId.

<h2>Usuarios:</h2>

    readUser:

        GET /{userId}

        Obtiene el usuario cuyo id es igual userId.

    readUsers:

        GET 

        Obtiene todos los usuarios.

    createUser:

        POST 

        Crea un usuario.

    deleteUser:

        DELETE /{userId}

        Elimina el usuario cuyo id es igual userId.

    updateUser:

        PUT /{userId}

        Actualiza el usuario cuyo id es igual userId.

<h2>Videos:</h2>

    readVideo:

        GET /{channelId}/videos/{videoId}

        Obtiene el video cuyo id es igual videoId.

    readVideos:

        GET /{channelId}/videos

        Obtiene todos los videos del canal cuyo id es igual channelId.

    createVideo:

        POST /{channelId}/videos

        Crea un video en el canal cuyo id es igual channelId.

    deleteVideo:

        DELETE /{channelId}/videos/{videoId}

        Elimina el video cuyo id es igual videoId.

    updateVideo:

        PUT /{channelId}/videos/{videoId}

        Actualiza el video cuyo id es igual videoId.

