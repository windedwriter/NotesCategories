Este proyecto consiste en una aplicación móvil desarrollada en Android (Java) que permite organizar notas dentro de categorías. Cada categoría puede contener múltiples notas, lo que representa una relación 1:N modelada con SQLite mediante la librería Room.

La aplicación permite:

Crear categorías.

Crear notas asignadas a una categoría específica.

Visualizar notas agrupadas por categoría.

Buscar notas dinámicamente por texto.

Eliminar notas individualmente.

Eliminar categorías junto con sus notas (gracias a la relación con eliminación en cascada).

Crear base de datos de categorias 
CREATE TABLE IF NOT EXISTS categories (
    category_id INTEGER PRIMARY KEY AUTOINCREMENT,
    category_name TEXT NOT NULL
);

Base de datos de Notas
CREATE TABLE IF NOT EXISTS notes (
    note_id INTEGER PRIMARY KEY AUTOINCREMENT,
    note_title TEXT NOT NULL,
    note_content TEXT,
    created_at INTEGER NOT NULL,
    category_id INTEGER NOT NULL,

    FOREIGN KEY(category_id) REFERENCES categories(category_id) 
        ON DELETE CASCADE
);

Interfaz para visualizar y añadir categorias


<img width="357" height="786" alt="image" src="https://github.com/user-attachments/assets/aa484504-a086-4a4e-b255-9da710a2ccb0" />


interfaz para visualizar notas dentro de la categoria

<img width="353" height="787" alt="image" src="https://github.com/user-attachments/assets/dc373639-4e34-4923-8ccc-20c3ddd5e332" />

Interfaz para añadir nota

<img width="351" height="784" alt="image" src="https://github.com/user-attachments/assets/1efcb26c-53d0-4597-974b-f27d71d52cb5" />

Intefaz para añadir categoria

<img width="355" height="787" alt="image" src="https://github.com/user-attachments/assets/67fc67db-1e67-4f59-bdad-41dc2215300f" />

Confirmacion de eliminacion

<img width="354" height="781" alt="image" src="https://github.com/user-attachments/assets/799d98bc-e4f3-4614-9e5f-5b3f4bd8d618" />

<img width="347" height="790" alt="image" src="https://github.com/user-attachments/assets/c327978b-6f5d-4ebf-aefe-742fe8f7b40e" />





