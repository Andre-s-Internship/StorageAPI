package org.example.Exception;

public enum ExceptionMessage {
    DirectoryNotFound {
        @Override
        public String toString() {
          return "Directory not found!";
            }
    },
    FileNotFound {
        @Override
        public String toString() {
            return "File not found!";
        }
    },
    InvalidFile {
        @Override
        public String toString() {
            return "File is invalid";
        }
    },
    FileAlreadyExists {
        @Override
        public String toString() {
            return "File with that name already exists";
        }
    },

}
