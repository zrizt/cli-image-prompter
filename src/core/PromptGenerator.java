package core;

import models.PromptTemplate;

public class PromptGenerator {

    // Method statis: menerima objek yg mewarisi PromptTemplate, lalu merakitnya jadi JSON
    public static String generateJson(PromptTemplate template) {
        StringBuilder json = new StringBuilder();

        json.append("{\n");
        json.append("  \"prompt_config\": {\n");
        json.append("    \"subject\": \"").append(template.getSubject()).append("\",\n");
        json.append("    \"camera_settings\": {\n");
        json.append("      \"model\": \"").append(template.getCameraModel()).append("\",\n");
        json.append("      \"focal_length\": \"").append(template.getFocalLength()).append("\",\n");
        json.append("      \"aperture\": \"").append(template.getAperture()).append("\"\n");
        json.append("    },\n");
        json.append("    \"environment\": {\n");
        json.append("      \"lighting\": \"").append(template.getLightingType()).append("\",\n");
        json.append("      \"color_grading\": \"").append(template.getColorGrading()).append("\"\n");
        json.append("    }\n");
        json.append("  }\n");
        json.append("}");

        return json.toString();
    }
}