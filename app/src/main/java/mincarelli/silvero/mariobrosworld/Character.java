package mincarelli.silvero.mariobrosworld;

/**
 * Represents a character in the application.
 * This class contains details about a character, including its image, name, description, and skills.
 */
public class Character {
    private int image;
    private String name;
    private String description;
    private String skills;

    /**
     * Constructs a new {@code Character} instance.
     *
     * @param image       The resource ID of the character's image.
     * @param name        The name of the character.
     * @param description A brief description of the character.
     * @param skills      A string describing the character's skills.
     */
    public Character(int image, String name, String description, String skills) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.skills = skills;
    }

    /**
     * Gets the resource ID of the character's image.
     *
     * @return The resource ID of the image.
     */
    public int getImage() {
        return image;
    }

    /**
     * Gets the name of the character.
     *
     * @return The name of the character.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the character.
     *
     * @return A brief description of the character.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the skills of the character.
     *
     * @return A string describing the character's skills.
     */
    public String getSkills() {
        return skills;
    }
}
