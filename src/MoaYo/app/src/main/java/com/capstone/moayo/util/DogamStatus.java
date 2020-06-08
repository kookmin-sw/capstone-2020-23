package com.capstone.moayo.util;

public enum DogamStatus {
    NonShare, // Dogam User do not Share
    Sharing, // User-shared Dogam
    Shared_Immutable, // Dogam shared by user, read-only, 1
    Shared_Mutable, // Dogam shared by user, 0
    Sharing_Mutable, // Dogam sharing , 2
    Sharing_Immutable // Dogam sharing , read-only, 3
}
