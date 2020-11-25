/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

package pl.sop.enums;

public enum ERole {
    ROLE_SUPERADMIN,  // Widzi wszystko i wszystkie uczelnie

    ROLE_ADMIN,       // Admin uczelni
    ROLE_DIRECTOR,    // Dyrektor np. instytutu
    ROLE_MODERATOR,   // Kierownik praktyk
    ROLE_SUPERVISER,  // Opiekun praktyk
    ROLE_STUDENT,     // Praktykant
    ROLE_USER         // User
}
