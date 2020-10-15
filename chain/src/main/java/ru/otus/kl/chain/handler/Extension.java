package ru.otus.kl.chain.handler;

public enum Extension {

    JSON ("json"),
    XML ("xml"),
    TXT ("txt"),
    CSV ("csv");

    private String value;

    Extension(String value){
        this.value = value;
    }

    public static Extension fromValue(String value) {
        for (Extension extension : Extension.values()) {
            if (extension.value.equals(value)) {
                return extension;
            }
        }

        throw new IllegalArgumentException("Unknown file extension");
    }
}
