package models;

public class SystemTemplate extends PromptTemplate {
    private String styleName; // Atribut khusus yang tidak dimiliki Parent

    public SystemTemplate(String styleName, String subject, String cameraModel, 
                          String focalLength, String aperture, String lightingType, String colorGrading) {
        // Keyword 'super' memanggil Constructor milik Parent
        super(subject, cameraModel, focalLength, aperture, lightingType, colorGrading);
        this.styleName = styleName;
    }

    // Method Overriding: Implementasi wajib dari abstract method
    @Override
    public String buildPromptString() {
        return "Style: " + styleName + ", Subject: " + subject + ", Camera: " + cameraModel + 
               ", Lens: " + focalLength + ", Aperture: " + aperture + 
               ", Lighting: " + lightingType + ", Color: " + colorGrading;
    }
}